import React from 'react';
import { Link } from "react-router-dom";

const NavBar = ({ token, handleLogout }) => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow mb-4">
      <div className="container d-flex justify-content-between align-items-center">
        <h1 className="text-white m-0 d-flex align-items-center">
          <img src="/fbi.svg" alt="FBI Logo" width="70" height="70" className="me-3" />  
        </h1>
        <div>
          {token ? (
            <button className="btn btn-outline-danger text-light border-light px-4 fw-bold rounded-pill" onClick={handleLogout}>
              Logout
            </button>
          ) : (
            <div className="d-flex gap-3">
              <Link className="btn btn-outline-info text-light border-light px-4 fw-bold rounded-pill" to="/register">
                Register
              </Link>
              <Link className="btn btn-outline-info text-light border-light px-4 fw-bold rounded-pill" to="/login">
                Login
              </Link>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
