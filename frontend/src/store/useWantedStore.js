import { create } from "zustand";
import { fetchWantedPersons } from "../api/fbiApi";

const useWantedStore = create((set) => ({
  wantedList: [],
  loading: false,
  fetchPersons: async (params) => {
    set({ loading: true });
    try {
      const data = await fetchWantedPersons(params);
      set({ wantedList: data.data });
    } catch (error) {
      console.error("Error fetching wanted persons:", error);
    } finally {
      set({ loading: false });
    }
  },
}));

export default useWantedStore;
