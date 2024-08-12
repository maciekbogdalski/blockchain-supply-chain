import React from 'react';

function UpdatePlantComponent() {
  return (
    <div>
      <h2>Update Plant State</h2>
      <form>
        <label>QR Code:</label>
        <input type="text" name="qr" required />
        <br />
        <label>New Status:</label>
        <input type="text" name="status" />
        <br />
        <button type="submit">Update Plant</button>
      </form>
    </div>
  );
}

export default UpdatePlantComponent;
