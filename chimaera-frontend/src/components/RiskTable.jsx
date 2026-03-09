import React from 'react';

const RiskTable = ({ riskData }) => {
    if (!riskData || Object.keys(riskData).length === 0) {
        return <p>No risk predictions available.</p>;
    }

    return (
        <table>
            <thead>
                <tr>
                    <th>Farm ID</th>
                    <th>Contamination Probability</th>
                </tr>
            </thead>
            <tbody>
                {Object.entries(riskData).map(([farmId, risk]) => {
                    const isHighRisk = risk > 0.6;
                    
                    return (
                        <tr key={farmId} className={isHighRisk ? "highlight-danger" : ""}>
                            <td>{farmId}</td>
                            <td>
                                {(risk * 100).toFixed(2)}%
                                {isHighRisk && <span className="badge-danger" style={{marginLeft: '10px'}}>High Risk</span>}
                            </td>
                        </tr>
                    );
                })}
            </tbody>
        </table>
    );
};

export default RiskTable;
