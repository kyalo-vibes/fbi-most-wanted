import React from 'react';

const Header = () => {
  return (
    <nav className="navbar navbar-dark bg-dark py-3 shadow">
      <div className="container text-center">
        
        <h1 className="text-white mx-auto">
        <img src="/fbi.svg" alt="FBI Logo" width="70" height="70" className="me-3" />  FBI Most Wanted Persons
        </h1>
      </div>
    </nav>
  );
};

export default Header;
