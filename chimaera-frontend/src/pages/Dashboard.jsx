
import React, { useEffect, useState } from 'react';
import { getFarms, getRisk, getOptimization } from '../services/api';
import FarmTable from '../components/FarmTable';
import RiskTable from '../components/RiskTable';
import OptimizationResult from '../components/OptimizationResult';

const Dashboard = () => {
    const [farms, setFarms] = useState([]);
    const [risks, setRisks] = useState({});
    const [optimized, setOptimized] = useState([]);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadAllData = async () => {
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

        loadAllData();
    }, []);

    if (loading) return <div className="loading">Loading dashboard data...</div>;
    if (error) return <div className="error">Error: {error}</div>;

    return (
        <div className="container">
            <h1>Ecosystem Dashboard</h1>

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
        </div>
    );
};

export default Dashboard;
