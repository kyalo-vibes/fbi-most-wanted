import React, { useContext } from 'react';
import FBIWantedContext from '../context/FBIWantedContext';

const Pagination = () => {
    const { page, setPage, totalPages } = useContext(FBIWantedContext);

    const handleFirst = () => setPage(1);
    const handleLast = () => setPage(totalPages);
    const handlePrevious = () => page > 1 && setPage(page - 1);
    const handleNext = () => page < totalPages && setPage(page + 1);

    // Show first 5 pages or up to totalPages
    const maxPages = 3;
    const startPage = Math.max(1, page - 2); 
    const endPage = Math.min(totalPages, startPage + maxPages - 1);

    return (
        <div className="d-flex justify-content-center align-items-center mt-4 mb-4">
            <button className="btn btn-outline-primary me-2" onClick={handleFirst} disabled={page === 1}>
                First
            </button>

            <button className="btn btn-outline-primary me-2" onClick={handlePrevious} disabled={page === 1}>
                «
            </button>

            {/* Page Numbers */}
            {[...Array(endPage - startPage + 1)].map((_, index) => {
                const pageNumber = startPage + index;
                return (
                    <button
                        key={pageNumber}
                        className={`btn mx-1 ${pageNumber === page ? 'btn-success text-white' : 'btn-outline-primary'}`}
                        onClick={() => setPage(pageNumber)}
                    >
                        {pageNumber}
                    </button>
                );
            })}

            <button className="btn btn-outline-primary ms-2" onClick={handleNext} disabled={page === totalPages}>
                »
            </button>

            <button className="btn btn-outline-primary ms-2" onClick={handleLast} disabled={page === totalPages}>
                Last
            </button>
        </div>
    );
};

export default Pagination;
