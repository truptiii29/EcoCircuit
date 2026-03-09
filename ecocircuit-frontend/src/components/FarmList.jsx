import React from 'react';

const FarmList = ({ risks, quarantine }) => {
    if (!risks) return <p>No risk data available.</p>;

    const farmIds = Object.keys(risks);

    return (
        <div style={{ marginTop: '20px' }}>
            <h2>Farm Risk Assessment</h2>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
                {farmIds.map(farmId => {
                    const isQuarantined = quarantine?.includes(farmId);
                    return (
                        <div key={farmId} style={{ 
                            padding: '15px', 
                            borderRadius: '8px',
                            border: '1px solid #ddd',
                            backgroundColor: isQuarantined ? '#ffebee' : '#fff',
                            boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
                        }}>
                            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                <strong>Farm {farmId}</strong>
                                <span>
                                    Risk Level: {(risks[farmId] * 100).toFixed(2)}%
                                </span>
                            </div>
                            {isQuarantined && <div style={{ marginTop: '10px', color: '#d32f2f', fontWeight: 'bold' }}>QUARANTINE RECOMMENDED</div>}
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default FarmList;
