import React from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const Register = () => {
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [message, setMessage] = React.useState("");
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/register', {
                email,
                password
            });

            setMessage("Registration successful. Redirecting to login...");
            setTimeout(() => navigate("/login"), 2000);

        } catch (error) {
            if (error.response && error.response.data) {
                setMessage(`Registration failed: ${error.response.data.message || "Try Again."}`);
            }
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-50">
            <div className="card shadow-lg border-0 rounded-lg p-4 text-center" style={{ maxWidth: "400px", width: "100%" }}>
                <h2 className="fw-bold mb-4">Register</h2>
                {message && <div className="alert alert-info">{message}</div>}
                <form onSubmit={handleRegister}>
                    <div className="mb-3 text-start">
                        <label className="fw-bold">Email</label>
                        <input type="email" className="form-control rounded-lg" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>
                    <div className="mb-3 text-start">
                        <label className="fw-bold">Password</label>
                        <input type="password" className="form-control rounded-lg" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>
                    <button type="submit" className="btn btn-outline-info text-dark fw-bold w-100 rounded-pill">Register</button>
                </form>
            </div>
        </div>
    );
};

export default Register;
