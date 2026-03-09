
import React, { useEffect, useState } from 'react';
import { getFarms, getRisk, getOptimization } from '../services/api';
import FarmTable from '../components/FarmTable';
import RiskTable from '../components/RiskTable';
import OptimizationResult from '../components/OptimizationResult';
import DynamicInputs from '../components/DynamicInputs';

const Dashboard = () => {
    const [farms, setFarms] = useState([]);
    const [risks, setRisks] = useState({});
    const [optimized, setOptimized] = useState([]);
    const [filters, setFilters] = useState(null);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const loadAllData = async (appliedFilters) => {
        setLoading(true);
        setError(null);
        setFilters(appliedFilters);
        try {
            const [farmsData, riskData, optData] = await Promise.all([
                getFarms(),
                getRisk(),
                getOptimization()
            ]);

            setFarms(farmsData);
            setRisks(riskData);
            setOptimized(optData);

        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container">
            <h1>Ecosystem Dashboard</h1>

            <section className="card">
                <DynamicInputs onApplyFilters={loadAllData} />
                {filters && (
                    <div style={{ marginTop: '10px', padding: '10px', backgroundColor: '#e9f5e9', borderRadius: '4px' }}>
                        <p><strong>Config Applied:</strong> Region: {filters.textInput || 'None'}, Threshold: {filters.numberInput}, Opt Level: {filters.sliderValue}</p>
                    </div>
                )}
                {error && <div className="error" style={{ marginTop: '20px' }}>Error: {error}</div>}
            </section>

            {loading ? (
                <div className="loading" style={{ marginTop: '30px' }}>Loading ecosystem and farm data...</div>
            ) : filters ? (
                <>
                    <section className="card">
                        <h2>Farm Data</h2>
                        <FarmTable farms={farms} />
                    </section>

                    <section className="card">
                        <h2>Risk Predictions</h2>
                        <RiskTable riskData={risks} />
                    </section>

                    <section className="card">
                        <h2>Quarantine Recommendations</h2>
                        <OptimizationResult quarantineFarms={optimized} />
                    </section>
                </>
            ) : (
                <div className="empty-state" style={{ marginTop: '30px' }}>
                    <p>Please enter your configuration parameters and click "Apply Settings" to view the farm and ecosystem data.</p>
                </div>
            )}
        </div>
    );
};

export default Dashboard;
