import * as React from 'react';
import {useContext, useState} from 'react';
import {AuthContext} from '../../context/AuthContext';

export default function SignIn() {

    const {tenantLogin} = useContext(AuthContext);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (event) => {
        event.preventDefault();

        const userData = {
            email,
            password,
        };

        tenantLogin(userData);
    };


    return (
        <div>
            <div className="mx-auto w-50 p-5 border border-3 mt-5">
                <h2 className="text-center fw-semibold mb-5">Sign Up</h2>
                <form onSubmit={handleSubmit}>
                    <div class="form-floating mb-3">
                        <input
                            type="email"
                            class="form-control"
                            id="email"
                            name="email"
                            onChange={(event) => setEmail(event.target.value)}
                            placeholder="name@example.com"
                            required
                        />
                        <label for="name">Email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="password"
                            class="form-control"
                            id="password"
                            name="password"
                            onChange={(event) => setPassword(event.target.value)}
                            placeholder="name@example.com"
                            required
                        />
                        <label for="name">Password</label>
                    </div>
                    <div className="form-group">
                        <button
                            type="submit"
                            className="btn btn-primary btn-block"
                        >
                            Đăng nhập
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}