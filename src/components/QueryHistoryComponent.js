import React from 'react';

function QueryHistoryComponent() {
  return (
    <div>
      <h2>Query Plant History</h2>
      <form>
        <label>QR Code:</label>
        <input type="text" name="qr" required />
        <br />
        <button type="submit">Query History</button>
      </form>
    </div>
  );
}

export default QueryHistoryComponent;
