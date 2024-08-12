import React from 'react';

function QueryPlantComponent() {
  return (
    <div>
      <h2>Query Plant State</h2>
      <form>
        <label>QR Code:</label>
        <input type="text" name="qr" required />
        <br />
        <button type="submit">Query Plant</button>
      </form>
    </div>
  );
}

export default QueryPlantComponent;
