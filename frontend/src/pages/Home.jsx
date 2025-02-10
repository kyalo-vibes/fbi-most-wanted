import React, { useEffect } from "react";
import useWantedStore from "../store/useWantedStore";
import WantedCard from "../components/WantedCard";

const Home = () => {
  const { wantedList, fetchPersons, loading } = useWantedStore();

  useEffect(() => {
    fetchPersons({ page: 1 });
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-500 to-blue-300 flex flex-col items-center justify-center px-4 py-12">
      {/* Hero Section */}
      <div className="text-center mb-10">
        <h1 className="text-5xl font-extrabold text-white drop-shadow-lg">
          🔎 FBI Most Wanted
        </h1>
        <p className="text-lg text-white mt-3 max-w-xl mx-auto opacity-90">
          Stay updated on the most wanted fugitives. Help bring justice by keeping an eye out.
        </p>
      </div>

      {/* Loader */}
      {loading && (
        <div className="flex justify-center">
          <div className="w-12 h-12 border-4 border-white border-t-transparent rounded-full animate-spin"></div>
        </div>
      )}

      {/* FBI Wanted List Grid */}
      {!loading && wantedList.length > 0 ? (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {wantedList.map((person) => (
            <WantedCard key={person.uid} person={person} />
          ))}
        </div>
      ) : (
        !loading && (
          <p className="text-center text-white text-lg mt-6">
            No wanted persons found.
          </p>
        )
      )}
    </div>
  );
};

export default Home;
