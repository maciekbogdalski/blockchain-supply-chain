import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import CreatePlantComponent from './components/CreatePlantComponent';
import UpdatePlantComponent from './components/UpdatePlantComponent';
import TransferOwnershipComponent from './components/TransferOwnershipComponent';
import QueryPlantComponent from './components/QueryPlantComponent';
import QueryHistoryComponent from './components/QueryHistoryComponent';

function App() {
  return (
    <Router>
      <div>
        <h1>Plant Tracking System</h1>
        <nav>
          <ul>
            <li><Link to="/create">Create Plant</Link></li>
            <li><Link to="/update">Update Plant</Link></li>
            <li><Link to="/transfer">Transfer Ownership</Link></li>
            <li><Link to="/query">Query Plant State</Link></li>
            <li><Link to="/history">Query Plant History</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/create" element={<CreatePlantComponent />} />
          <Route path="/update" element={<UpdatePlantComponent />} />
          <Route path="/transfer" element={<TransferOwnershipComponent />} />
          <Route path="/query" element={<QueryPlantComponent />} />
          <Route path="/history" element={<QueryHistoryComponent />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
