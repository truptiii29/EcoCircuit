from flask import Flask, request, jsonify
import pandas as pd
from sklearn.ensemble import RandomForestClassifier

app = Flask(__name__)

df = pd.read_csv('climate_training_data.csv')
X = df[['temperature', 'humidity', 'wind_speed']]
y = df['contamination']

model = RandomForestClassifier(random_state=42)
model.fit(X, y)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    
    temp = data.get('temperature')
    humidity = data.get('humidity')
    wind_speed = data.get('windSpeed')
    
    prob = model.predict_proba([[temp, humidity, wind_speed]])[0][1]
    
    return jsonify({'contaminationProbability': prob})

if __name__ == '__main__':
    app.run(port=5000)
