import React, { useState } from 'react';

const DynamicInputs = ({ onApplyFilters }) => {
    const [textInput, setTextInput] = useState('');
    const [numberInput, setNumberInput] = useState(50);
    const [sliderValue, setSliderValue] = useState(5);

    const handleApply = () => {
        if (onApplyFilters) {
            onApplyFilters({ textInput, numberInput, sliderValue });
        }
    };

    return (
        <div className="card dynamic-inputs-card">
            <h2>Dynamic Configuration parameters</h2>
            <div className="input-group">
                <label htmlFor="text-input">Region Filter (Text): </label>
                <input 
                    type="text" 
                    id="text-input" 
                    value={textInput} 
                    onChange={(e) => setTextInput(e.target.value)} 
                    placeholder="Enter region..."
                />
            </div>
            
            <div className="input-group">
                <label htmlFor="number-input">Risk Threshold (Number): </label>
                <input 
                    type="number" 
                    id="number-input" 
                    value={numberInput} 
                    onChange={(e) => setNumberInput(Number(e.target.value))} 
                />
            </div>

            <div className="input-group">
                <label htmlFor="slider-input">Optimization Level (Slider): {sliderValue}</label>
                <input 
                    type="range" 
                    id="slider-input" 
                    min="1" 
                    max="10" 
                    value={sliderValue} 
                    onChange={(e) => setSliderValue(Number(e.target.value))} 
                />
            </div>

            <button onClick={handleApply} className="apply-btn">Apply Settings</button>
        </div>
    );
};

export default DynamicInputs;
