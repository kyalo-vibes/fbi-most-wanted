import React, { useEffect, useState } from 'react';
import Header from "../components/Header";
import SearchFilter from "../components/SearchFilter";
import PersonList from "../components/PersonList";
import Pagination from '../components/Pagination';

const MostWanted = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const token = localStorage.getItem("token");

    

  return (
    <div className="bg-light min-vh-100">
      {/* Header Section */}
      <Header />

      {/* Main Content */}
      <div className="container mt-4">
        
        {/* Search & Filter Section */}
        <div className="row justify-content-center">
          <div className="col-md-10">
            <SearchFilter />
          </div>
        </div>

        {/* Person List */}
        <div className="row mt-4">
          <PersonList />
        </div>

        {/* Pagination */}
        <div className="row justify-content-center mt-4">
          <div className="col-md-6 text-center">
            <Pagination />
          </div>
        </div>

      </div>
    </div>
  );
};

export default MostWanted;
