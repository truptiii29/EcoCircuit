import React from 'react';

const FarmTable = ({ farms }) => {
    if (!farms || farms.length === 0) {
        return <p>No farm data available.</p>;
    }

    return (
        <table>
            <thead>
                <tr>
                    <th>Farm ID</th>
                    <th>Temperature (°C)</th>
                    <th>Humidity (%)</th>
                    <th>Wind Speed (m/s)</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                </tr>
            </thead>
            <tbody>
                {farms.map(farm => (
                    <tr key={farm.farmId}>
                        <td>{farm.farmId}</td>
                        <td>{farm.temperature}</td>
                        <td>{farm.humidity}</td>
                        <td>{farm.windSpeed}</td>
                        <td>{farm.latitude}</td>
                        <td>{farm.longitude}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default FarmTable;
