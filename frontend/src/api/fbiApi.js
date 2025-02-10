import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/v1.0.0/wanted";

export const fetchWantedPersons = async (params = {}) => {
  const response = await axios.get(API_BASE_URL, { params });
  return response.data;
};
