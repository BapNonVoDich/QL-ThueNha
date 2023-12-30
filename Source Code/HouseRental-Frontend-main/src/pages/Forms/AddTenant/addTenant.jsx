import React, {useContext, useEffect, useState} from 'react';
import './AddTenant.css'; // Import custom CSS file for styling
import NavBar from '../../../components/NavBar-Main/Navbar';
import {useLocation, useNavigate} from 'react-router-dom';
import {AuthContext} from '../../../context/AuthContext';

const AddTenant = ({propertyId, landlordId}) => {
    const [data, setData] = useState({
        name: "",
        email: "",
        phone: "",
        password: "",
        propertyId: "",
        landlordId: ""
    });

    const {user} = useContext(AuthContext);
    const navigate = useNavigate();
    const location = useLocation();


    const handleChange = (e) => {
        setData({...data, [e.target.name]: e.target.value});
    };

    useEffect(() => {
        if (location.state && location.state.propertyId) {
            const propertyId = location.state.propertyId;
            setData({
                propertyId: propertyId,
                landlordId: user.id,
            });
        }
    }, [location.state]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(
            data.name +
            ", " +
            data.email +
            ", " +
            data.phone +
            ", " +
            data.password +
            ", " +
            data.propertyId +
            ", " +
            data.landlordId
        );
        const response = await fetch(
            `http://localhost:8080/api/tenant/add`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    name: data.name,
                    email: data.email,
                    password: data.password,
                    phone: data.phone,
                    landlordId: data.landlordId,
                    propertyId: data.propertyId,
                }),
            }
        );
        const dataJson = await response.json();
        console.log(dataJson);
        setData({name: "", type: "", city: "", address: "", rent: ""});
        navigate("/property");
    };

    return (
        <div>
            <NavBar/>
            <div className="mx-auto w-50 p-5 border border-3 mt-5">
                <h2 className="text-center fw-semibold mb-5">Thêm khách</h2>
                <form onSubmit={handleSubmit} className="my-form">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="name"
                            name="name"
                            onChange={handleChange}
                            placeholder="name@example.com"
                            required
                        />
                        <label for="name">Tên-</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="email"
                            class="form-control"
                            id="email"
                            name="email"
                            onChange={handleChange}
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
                            onChange={handleChange}
                            placeholder="name@example.com"
                            required
                        />
                        <label for="name">Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="phone"
                            name="phone"
                            onChange={handleChange}
                            placeholder="name@example.com"
                            required
                        />
                        <label for="name">Số đện thoại</label>
                    </div>
                    <button type="submit" className="btn btn-primary">
                        Submit
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AddTenant;
