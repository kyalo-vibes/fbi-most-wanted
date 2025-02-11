import React from "react";
import { Link } from "react-router-dom";

const PersonCard = ({ person }) => {
  const imageUrl = person.imageUrl ? person.imageUrl : "/placeholder.png";

  return (
    <div className="col-md-3 mb-4">
      <div className="card h-100 shadow-sm border-0 rounded-lg overflow-hidden person-card">
        {/* Image Section */}
        <div className="position-relative">
          <img
            src={imageUrl}
            className="card-img-top img-fluid"
            alt={person.title}
            style={{ height: "250px", objectFit: "cover", borderTopLeftRadius: "10px", borderTopRightRadius: "10px" }}
          />
          {/* <span className="badge bg-danger position-absolute top-0 start-0 m-2">
            Wanted
          </span> */}
        </div>

        {/* Card Body */}
        <div className="card-body text-center">
          <h5 className="card-title fw-bold">{person.title}</h5>
        </div>

        {/* Info List */}
        <ul className="list-group list-group-flush text-center">
          <li className="list-group-item"><strong>Sex:</strong> {person.sex || "N/A"}</li>
          <li className="list-group-item"><strong>Race:</strong> {person.race || "N/A"}</li>
          <li className="list-group-item"><strong>Hair:</strong> {person.hairColor || "N/A"}</li>
          <li className="list-group-item"><strong>Eyes:</strong> {person.eyeColor || "N/A"}</li>
        </ul>

        {/* Button Section */}
        <div className="card-body text-center">
          <Link to={`/person/${person.uid}`} className="btn btn-outline-primary w-75 fw-bold">
            View Details
          </Link>
        </div>
      </div>
    </div>
  );
};

export default PersonCard;
