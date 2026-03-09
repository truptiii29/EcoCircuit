from flask import Flask, request, jsonify
import networkx as nx
from qiskit_aer import Aer
from qiskit_optimization.applications import Maxcut
from qiskit_algorithms import QAOA
from qiskit_algorithms.optimizers import COBYLA
from qiskit.primitives import Sampler
from qiskit_optimization.converters import QuadraticProgramToQubo

app = Flask(__name__)

@app.route('/maxcut', methods=['POST'])
def get_farms_to_isolate():
    data = request.get_json()
    farm_ids = data.get('farmIds', [])
    risk_probs = data.get('riskProbabilities', [])
    
    if len(farm_ids) != len(risk_probs) or not farm_ids:
        return jsonify({'farmsToIsolate': []})

    # Construct complete graph with risk probabilities as weights (simple model)
    n = len(farm_ids)
    graph = nx.Graph()
    for i in range(n):
        for j in range(i + 1, n):
            weight = (risk_probs[i] + risk_probs[j]) / 2.0
            graph.add_edge(i, j, weight=weight)
            
    max_cut = Maxcut(graph)
    qp = max_cut.to_quadratic_program()
    
    qubo = QuadraticProgramToQubo().convert(qp)
    optimizer = COBYLA()
    sampler = Sampler()
    
    qaoa = QAOA(sampler, optimizer, reps=1)
    
    # Solve QUBO
    from qiskit_optimization.algorithms import MinimumEigenOptimizer
    optimizer = MinimumEigenOptimizer(qaoa)
    result = optimizer.solve(qubo)
    
    # Extract solution
    cut = max_cut.interpret(result)
    
    # Determine isolated farms
    isolated_farms = []
    for i, in_cut in enumerate(cut):
        if in_cut:
            isolated_farms.append(farm_ids[i])
            
    return jsonify({'farmsToIsolate': isolated_farms})

if __name__ == '__main__':
    app.run(port=6000)
