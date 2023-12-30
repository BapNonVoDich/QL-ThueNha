import React from 'react';
import {FaExclamationCircle} from 'react-icons/fa';
import './NotLoggedIn.css'; // Import the CSS file for styling

const NotLoggedIn = () => {
    const handleGoToLandingPage = () => {
        // Logic to navigate to the landing page
        window.location.href = '/landing';
    };

    return (
        <div className="not-logged-in-container">
            <FaExclamationCircle className="not-logged-in-icon"/>
            <h2 className="not-logged-in-heading">Bạn chưa đăng nhập</h2>
            <p className="not-logged-in-text">Xin hãy Login để sử dụng chức năng!</p>
            <button className="go-to-landing-btn" onClick={handleGoToLandingPage}>
                Đi đến trang chủ
            </button>
        </div>
    );
};

export default NotLoggedIn;
