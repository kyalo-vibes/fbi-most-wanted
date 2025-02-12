import React, { useContext } from 'react';
import FBIWantedContext from '../context/FBIWantedContext';

const SearchFilter = () => {
    const { searchTerm, setSearchTerm, pendingFilters, setPendingFilters, applyFilters, clearFilters } = useContext(FBIWantedContext);

    const handleSearch = (e) => {
        setSearchTerm(e.target.value);
    };

    const handleFilterChange = (e) => {
        setPendingFilters({
            ...pendingFilters,
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

                {/* Filters Section */}
                <div className="container p-3 bg-light rounded shadow-sm">
                <div className="row g-6">
                    
                    {/* Race Filter */}
                    <div className="col-md-5 col-lg-4">
                    <label htmlFor="raceFilter" className="form-label fw-semibold">Race</label>
                    <select id="raceFilter" name="race" className="form-select" value={pendingFilters.race || ""} onChange={handleFilterChange}>
                        <option value="">All Races</option>
                        <option value="White">White</option>
                        <option value="Black">Black</option>
                        <option value="Asian">Asian</option>
                        <option value="Hispanic">Hispanic</option>
                    </select>
                    </div>

                    {/* Hair Color Filter */}
                    <div className="col-md-5 col-lg-4">
                    <label htmlFor="hairColorFilter" className="form-label fw-semibold">Hair Color</label>
                    <select id="hairColorFilter" name="hairColor" className="form-select" value={pendingFilters.hairColor || ""} onChange={handleFilterChange}>
                        <option value="">All Hair Colors</option>
                        <option value="Black">Black</option>
                        <option value="Brown">Brown</option>
                    </select>
                    </div>

                    {/* Eye Color Filter */}
                    <div className="col-md-5 col-lg-4">
                    <label htmlFor="eyeColorFilter" className="form-label fw-semibold">Eye Color</label>
                    <select id="eyeColorFilter" name="eyeColor" className="form-select" value={pendingFilters.eyeColor || ""} onChange={handleFilterChange}>
                        <option value="">All Eye Colors</option>
                        <option value="Brown">Brown</option>
                        <option value="Blue">Blue</option>
                        <option value="Green">Green</option>
                        <option value="Hazel">Hazel</option>
                    </select>
                    </div>

                    {/* Sex Filter */}
                    <div className="col-md-5 col-lg-4">
                    <label htmlFor="sexFilter" className="form-label fw-semibold">Sex</label>
                    <select id="sexFilter" name="sex" className="form-select" value={pendingFilters.sex || ""} onChange={handleFilterChange}>
                        <option value="">All Genders</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                    </div>

                    {/* Categories Filter */}
                    <div className="col-md-5 col-lg-4">
                    <label htmlFor="categoryFilter" className="form-label fw-semibold">Category</label>
                    <select id="categoryFilter" name="category" className="form-select" value={pendingFilters.category || ""} onChange={handleFilterChange}>
                        <option value="">All Categories</option>
                        <option value="Violent Crimes">Violent Crimes</option>
                        <option value="Missing Persons">Missing Persons</option>
                        <option value="Cyber Crimes">Cyber Crimes</option>
                        <option value="White-Collar Crimes">White-Collar Crimes</option>
                        <option value="Terrorism">Terrorism</option>
                        <option value="Human Trafficking">Human Trafficking</option>
                        <option value="Most Wanted">Most Wanted</option>
                        <option value="Seeking Information">Seeking Information</option>
                    </select>
                    </div>
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
                            value={pendingFilters.ageMin || ""}
                            onChange={handleFilterChange}
                        />
                    </div>
                    <div className="col-md-6">
                        <input
                            type="number"
                            name="ageMax"
                            className="form-control"
                            placeholder="Max Age"
                            value={pendingFilters.ageMax || ""}
                            onChange={handleFilterChange}
                        />
                    </div>
                </div>

                <div className="d-flex justify-content-center gap-3 mt-3">
                    <button className="btn btn-outline-primary w-50 fw-bold" onClick={applyFilters}>
                        Apply Filters
                    </button>
                    <button className="btn btn-outline-primary w-50 fw-bold" onClick={clearFilters}>
                        Clear Filters
                    </button>
                    </div>
            </div>
        </div>
    );
}

export default SearchFilter;
