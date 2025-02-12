import axios from 'axios';
import React, { createContext, useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const FBIWantedContext = createContext();

export const FBIWantedProvider = ({ children }) => {
    const [total, setTotal] = useState(0);
    const [filters, setFilters] = useState({});
    const [pendingFilters, setPendingFilters] = useState({});
    const [persons, setPersons] = useState([]);
    const [loading, setLoading] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");
    const [page, setPage] = useState(1);

    const itemsPerPage = 10;
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    const fetchPersons = async () => {  
        if (!token) return;
        setLoading(true);
        try {

          const params = {
            page,
            name: searchTerm || undefined,
            race: filters.race || undefined,
            sex: filters.sex || undefined,
            ageMin: filters.ageMin || undefined,
            ageMax: filters.ageMax || undefined,
            hairColor: filters.hairColor || undefined,
            eyeColor: filters.eyeColor || undefined,
            category: filters.category || undefined,
          };

          // Removes empty filters
          Object.keys(params).forEach((key) => {
            if (!params[key]) {
              delete params[key];
            }
          });

          const response = await axios.get(`http://localhost:8080/api/v1.0.0/wanted`,
              { params,
                headers: {
                  Authorization: `Bearer ${token}`, 
                },
              }
          );

          const totalResults = response.data.totalResults || 0;
          const totalPagesFromAPI = response.data.totalPages || Math.ceil(totalResults / itemsPerPage);

          if (page > totalPagesFromAPI) {
              setPage(totalPagesFromAPI);s
          }

          setPersons(response.data.data);
          setTotal(totalResults);

        } catch (error) {
            console.error("Error fetching data:", error);
            if (error.response?.status === 401 || error.response?.status === 403) {
              localStorage.removeItem("token"); 
              navigate("/login"); 
          }
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
      fetchPersons();
    }, [token, page, filters]);

    const applyFilters = () => {
      setFilters(pendingFilters);
    }

    const clearFilters = () => {
      setFilters({});
      setPendingFilters({});
    };

    const totalPages = Math.max(1, Math.ceil(total / itemsPerPage));
    

  return (
    <FBIWantedContext.Provider
        value={{
            persons,
            loading,
            searchTerm,
            setSearchTerm,
            filters,
            pendingFilters,
            setPendingFilters,
            applyFilters,
            clearFilters,
            page,
            setPage,
            totalPages,
        }}
    >
      {children}
    </FBIWantedContext.Provider>
  )
};


export default FBIWantedContext;
