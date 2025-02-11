import axios from 'axios';
import React, { createContext, useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const FBIWantedContext = createContext();

export const FBIWantedProvider = ({ children }) => {

    const [persons, setPersons] = useState([]);
    const [loading, setLoading] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");
    const [filters, setFilters] = useState({});
    const [page, setPage] = useState(1);
    const [total, setTotal] = useState(0);
    const [filteredPersons, setFilteredPersons] = useState([]);

    const itemsPerPage = 10;
    const navigate = useNavigate();

    const token = localStorage.getItem("token");

    const fetchPersons = async () => {  
        if (!token) return;
        setLoading(true);
        try {

          const params = {
            page,
            title: searchTerm,
            ...filters,
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
            if (error.response?.status === 401) {
              localStorage.removeItem("token"); 
              navigate("/login"); 
          }
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
      fetchPersons();
    }, [token, page, searchTerm, filters]);

    useEffect(() => {
      const filterData = () => {
          const filtered = persons.filter(person => {
              const matchesSearch = 
                  !searchTerm || person.title?.toLowerCase().includes(searchTerm.toLowerCase());

      
              const matchesRace = !filters?.race || person.race?.toLowerCase() === filters.race.toLowerCase();
              const matchesHairColor = !filters?.hairColor || (person.hairColor && person.hairColor.toLowerCase().includes(filters.hairColor.toLowerCase()));
              const matchesEyeColor = !filters?.eyeColor || person.eyeColor?.toLowerCase() === filters.eyeColor.toLowerCase()
              const matchesSex = !filters?.sex || person.sex?.toLowerCase() === filters.sex.toLowerCase()
              const matchesAgeMin = !filters?.ageMin || (person.ageMin && parseInt(person.ageMin) >= parseInt(filters.ageMin))
              const matchesAgeMax = !filters?.ageMax || (person.ageMax && parseInt(person.ageMax) <= parseInt(filters.ageMax));

              return matchesSearch && (matchesRace || matchesHairColor || matchesEyeColor || matchesSex || matchesAgeMin || matchesAgeMax);
          });

          setFilteredPersons(filtered);
      };

      filterData();
  }, [filters, persons, searchTerm]);

    const totalPages = Math.max(1, Math.ceil(total / itemsPerPage));
    

  return (
    <FBIWantedContext.Provider
        value={{
            persons: filteredPersons,
            loading,
            searchTerm,
            setSearchTerm,
            filters,
            setFilters,
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
