import React from 'react';

function TransferOwnershipComponent() {
  return (
    <div>
      <h2>Transfer Plant Ownership</h2>
      <form>
        <label>QR Code:</label>
        <input type="text" name="qr" required />
        <br />
        <label>New Owner:</label>
        <input type="text" name="owner" />
        <br />
        <button type="submit">Transfer Ownership</button>
      </form>
    </div>
  );
}

export default TransferOwnershipComponent;
