import React, { useContext } from 'react';
import FBIWantedContext from '../context/FBIWantedContext';

const SearchFilter = () => {
    const { searchTerm, setSearchTerm, filters, setFilters } = useContext(FBIWantedContext);

    const handleSearch = (e) => {
        setSearchTerm(e.target.value);
    };

    const handleFilterChange = (e) => {
        setFilters({
            ...filters,
            [e.target.name]: e.target.value
        });
    };

    return (
        <div className="container mt-4">
            <div className="card shadow p-3">
                <h4 className="card-title text-center mb-3">Search & Filter</h4>
                
                {/* Search Bar */}
                <div className="mb-3">
                    <input 
                        type="text"
                        placeholder="🔍 Search by name or title..."
                        className="form-control"
                        value={searchTerm}
                        onChange={handleSearch}
                    />
                </div>

                {/* Filters */}
                <div className="row g-2">
                    {/* Race Filter */}
                    <div className="col-md-3">
                        <select name="race" className="form-select" value={filters.race} onChange={handleFilterChange}>
                            <option value="">Filter by Race</option>
                            <option value="White">White</option>
                            <option value="Black">Black</option>
                            <option value="Asian">Asian</option>
                            <option value="Hispanic">Hispanic</option>
                        </select>
                    </div>

                    {/* Hair Color Filter */}
                    <div className="col-md-3">
                        <select name="hairColor" className="form-select" value={filters.hairColor} onChange={handleFilterChange}>
                            <option value="">Filter by Hair Color</option>
                            <option value="Black">Black</option>
                            <option value="Brown">Brown</option>
                        </select>
                    </div>

                    {/* Eye Color Filter */}
                    <div className="col-md-3">
                        <select name="eyeColor" className="form-select" value={filters.eyeColor} onChange={handleFilterChange}>
                            <option value="">Filter by Eye Color</option>
                            <option value="Brown">Brown</option>
                            <option value="Blue">Blue</option>
                            <option value="Green">Green</option>
                            <option value="Hazel">Hazel</option>
                        </select>
                    </div>

                    {/* Sex Filter */}
                    <div className="col-md-3">
                        <select name="sex" className="form-select" value={filters.sex} onChange={handleFilterChange}>
                            <option value="">Filter by Sex</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                </div>

                {/* Age Range Filter */}
                <div className="row g-2 mt-3">
                    <label className="form-label">Age Range:</label>
                    <div className="col-md-6">
                        <input
                            type="number"
                            name="ageMin"
                            className="form-control"
                            placeholder="Min Age"
                            value={filters.ageMin}
                            onChange={handleFilterChange}
                        />
                    </div>
                    <div className="col-md-6">
                        <input
                            type="number"
                            name="ageMax"
                            className="form-control"
                            placeholder="Max Age"
                            value={filters.ageMax}
                            onChange={handleFilterChange}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchFilter;
