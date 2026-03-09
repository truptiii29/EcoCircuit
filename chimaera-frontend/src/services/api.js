const BASE_URL = 'http://localhost:8080';

export const getFarms = async () => {
    try {
        const response = await fetch(`${BASE_URL}/farms`);
        if (!response.ok) {
            throw new Error(`Failed to fetch farms: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Error fetching farms:", error);
        throw error;
    }
};

export const getRisk = async () => {
    try {
        const response = await fetch(`${BASE_URL}/risk`);
        if (!response.ok) {
            throw new Error(`Failed to fetch risk predictions: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Error fetching risks:", error);
        throw error;
    }
};

export const getOptimization = async () => {
    try {
        const response = await fetch(`${BASE_URL}/optimize`);
        if (!response.ok) {
            throw new Error(`Failed to fetch isolation recommendations: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Error fetching optimizations:", error);
        throw error;
    }
};
