import React, { useContext } from 'react';
import FBIWantedContext from '../context/FBIWantedContext';
import PersonCard from './PersonCard';

const PersonList = () => {

    const {persons, loading } = useContext(FBIWantedContext);

    if (loading) return (
      <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="text-center">
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
                <p className="mt-2 text-muted">Fetching most wanted persons...</p>
            </div>
        </div>
    );


  return (
    <div className='container'>
      <div className='row'>
      {persons.length > 0 ? (    
        persons.map(person => (
          <PersonCard key={person.uid} person={person} />
        ))
      ) : (
        <div className="d-flex flex-column align-items-center justify-content-center mt-4">
          <div className="card shadow-sm p-4 text-center w-50 border-0">
            <div className="card-body">
              <i className="bi bi-search fs-1 text-primary"></i>
              <h5 className="mt-3 fw-bold text-muted">No Results Found</h5>
              <p className="text-secondary">Try adjusting your search criteria or filters.</p>
            </div>
          </div>
        </div>
      )}
      </div>
    </div>
  )
}

export default PersonList;
