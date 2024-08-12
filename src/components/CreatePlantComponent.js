import React, { useState } from 'react';
import axios from 'axios';

function CreatePlantComponent() {
  const [plantData, setPlantData] = useState({ qr: '', extraInfo: '' });

  const handleChange = (e) => {
    setPlantData({ ...plantData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('/api/createPlant', plantData)
      .then(response => {
        alert('Plant created successfully!');
      })
      .catch(error => {
        console.error('There was an error creating the plant!', error);
      });
  };

  return (
    <div>
      <h2>Create Plant Tracking</h2>
      <form onSubmit={handleSubmit}>
        <label>QR Code:</label>
        <input type="text" name="qr" value={plantData.qr} onChange={handleChange} required />
        <br />
        <label>Extra Info:</label>
        <input type="text" name="extraInfo" value={plantData.extraInfo} onChange={handleChange} />
        <br />
        <button type="submit">Create Plant</button>
      </form>
    </div>
  );
}

export default CreatePlantComponent;
