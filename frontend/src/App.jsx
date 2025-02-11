import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { FBIWantedProvider } from './context/FBIWantedContext';
import PersonDetailPage from './pages/PersonDetailPage';
import { Link } from "react-router-dom";
import Login from './pages/Login';
import Register from './pages/Register';
import NavBar from './components/NavBar';
import MostWanted from './pages/MostWanted';

function App() {

  const [token, setToken] = useState(localStorage.getItem('token'));

  useEffect(() => {
    setToken(localStorage.getItem('token'));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setToken(null);
  }
  
  return (  
    
      <Router>
        <FBIWantedProvider>
          <div className='container mt-4'>
            <NavBar token={token} handleLogout={handleLogout} />
            <Routes>
              <Route path="/" element={token ? <MostWanted /> : <Navigate to="/login" />} />
              <Route path="/person/:id" element={token ? <PersonDetailPage /> : <Navigate to="/login" />} />
              <Route path="/register" element={<Register />} />
              <Route path="/login" element={<Login setToken={setToken}/>} />
            </Routes>
          </div>
        </FBIWantedProvider>
      </Router>
  );
};

export default App;
