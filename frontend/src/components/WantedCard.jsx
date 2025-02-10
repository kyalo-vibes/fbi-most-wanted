import React from "react";

const WantedCard = ({ person }) => {
  return (
    <div className="border p-4 rounded-md shadow-md">
      <img src={person.imageUrl} alt={person.title} className="w-full h-48 object-cover rounded-md" />
      <h2 className="text-lg font-bold mt-2">{person.title}</h2>
      <p className="text-gray-600">{person.nationality}</p>
    </div>
  );
};

export default WantedCard;
