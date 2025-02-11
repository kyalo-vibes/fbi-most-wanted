import React from 'react';
import axios from 'axios';	
import { useNavigate } from "react-router-dom";

const Login = ({ setToken }) => {
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [message, setMessage] = React.useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {
                email,
                password
            });
            if (response.data.token) {
                localStorage.setItem('token', response.data.token);
                setToken(response.data.token);
                setMessage("Login successful. Redirecting to home page...");
                navigate("/");
            } else {
                setMessage("Login failed: No token received.");
            }
        } catch (error) {
            console.error("Login error:", error.response?.data || error.message); // Log full error
            setMessage(error.response?.data?.message || "Login failed. Check credentials.");
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-50">
            <div className="card shadow-lg border-0 rounded-lg p-4 text-center" style={{ maxWidth: "400px", width: "100%" }}>
                <h2 className="fw-bold mb-4">Login</h2>
                {message && <div className="alert alert-info">{message}</div>}
                <form onSubmit={handleLogin}>
                    <div className="mb-3 text-start">
                        <label className="fw-bold">Email</label>
                        <input type="email" className="form-control rounded-lg" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>
                    <div className="mb-3 text-start">
                        <label className="fw-bold">Password</label>
                        <input type="password" className="form-control rounded-lg" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>
                    <button type="submit" className="btn btn-outline-info text-dark fw-bold w-100 rounded-pill">Login</button>
                </form>
            </div>
        </div>
    );
};

export default Login;
