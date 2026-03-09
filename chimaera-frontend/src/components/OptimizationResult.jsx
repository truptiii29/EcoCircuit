import React from 'react';

const OptimizationResult = ({ quarantineFarms }) => {
    if (!quarantineFarms || quarantineFarms.length === 0) {
        return (
            <div className="empty-state">
                <p>No farms require quarantine based on current optimization.</p>
            </div>
        );
    }

    return (
        <div>
            {quarantineFarms.map(farmId => (
                <div key={farmId} className="optimization-card">
                    <div>
                        <h3>Farm {farmId}</h3>
                        <p>High probability of contamination</p>
                    </div>
                    <div>
                        <span className="badge-danger">
                            Isolate Immediately
                        </span>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default OptimizationResult;
