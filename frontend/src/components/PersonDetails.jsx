import React, { useContext } from "react";
import { useParams, Link } from "react-router-dom";
import FBIWantedContext from "../context/FBIWantedContext";

const PersonDetails = () => {
  const { persons } = useContext(FBIWantedContext);
  const { id } = useParams();
  const person = persons.find((person) => person.uid === id);

  if (!person)
    return (
      <div className="container text-center mt-5">
        <div className="alert alert-danger shadow-sm">
          <h2 className="fw-bold">❌ Person Not Found</h2>
          <p>The requested record does not exist.</p>
          <Link to="/" className="btn btn-outline-primary">Back to List</Link>
        </div>
      </div>
    );

  const imageUrl = person.imageUrl || "/placeholder.png";

  return (
    <div className="container mt-5 mb-5">
      <div className="card shadow-lg border-0 rounded-lg overflow-hidden">
        <div className="row g-0">
          {/* Image Section */}
          <div className="col-md-4 position-relative">
            <img
              src={imageUrl}
              className="img-fluid h-100 w-100"
              alt={person.title}
              style={{ objectFit: "cover" }}
            />
            <span className="badge bg-danger position-absolute top-0 start-0 m-3 p-2 shadow">
              {person.category}
            </span>
          </div>

          {/* Details Section */}
          <div className="col-md-8">
            <div className="card-body">
              <h1 className="card-title text-primary fw-bold">{person.title}</h1>
              <p className="card-text text-muted fst-italic">
                {person.description || "No description available."}
              </p>

              {/* Additional Information */}
              <h4 className="mt-4 border-bottom pb-2">🔎 Additional Information</h4>
              <ul className="list-group list-group-flush">
                <li className="list-group-item"><strong>👤 Race:</strong> {person.race || "N/A"}</li>
                <li className="list-group-item"><strong>💇‍♂️ Hair:</strong> {person.hairColor || "N/A"}</li>
                <li className="list-group-item"><strong>👀 Eyes:</strong> {person.eyeColor || "N/A"}</li>
                <li className="list-group-item"><strong>⚧️ Sex:</strong> {person.sex || "N/A"}</li>
                <li className="list-group-item"><strong>📅 Age Range:</strong> {person.ageMin} - {person.ageMax}</li>
                <li className="list-group-item"><strong>🌍 Nationality:</strong> {person.nationality || "N/A"}</li>
                <li className="list-group-item"><strong>📍 Place of Birth:</strong> {person.placeOfBirth || "Unknown"}</li>
                <li className="list-group-item"><strong>🕵️‍♂️ Aliases:</strong> {person.aliases?.length ? person.aliases.join(", ") : "None"}</li>
                <li className="list-group-item"><strong>📖 Subject:</strong> {person.subjects?.length ? person.subjects.join(", ") : "None"}</li>
                <li className="list-group-item"><strong>💼 Occupation:</strong> {person.occupations?.length ? person.occupations.join(", ") : "Unknown"}</li>
                {person.field_offices?.length > 0 && (
                  <li className="list-group-item"><strong>🏢 Field Offices:</strong> {person.fieldOffices.join(", ")}</li>
                )}
              </ul>

              {/* External Files Section */}
              {person.externalFiles?.length > 0 && (
                <>
                  <h4 className="mt-4 border-bottom pb-2">📂 External Files</h4>
                  <ul className="list-group list-group-flush">
                    {person.externalFiles.map((file, index) => {
                      const fileName = file.name && file.name.trim() ? file.name : "N/A";
                      return (
                        <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                        <span><strong>{fileName}</strong></span>
                        <a href={file.url} target="_blank" rel="noopener noreferrer" className="btn btn-outline-primary w-25 fw-bold">
                          📥 Download
                        </a>
                      </li>
                      );
                    })}
                  </ul>
                </>
              )}

              {/* Back Button */}
              <div className="mt-4 text-center">
                <Link to="/" className="btn btn-outline-primary w-50 fw-bold">
                🔙 Back to List
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PersonDetails;
