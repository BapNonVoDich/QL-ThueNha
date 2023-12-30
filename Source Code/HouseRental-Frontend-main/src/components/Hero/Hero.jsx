import React from 'react'
import "./Hero.css"
import {Container} from 'react-bootstrap'


function Hero() {
    return (
        <>
            <section className='hero'>
                <div className='container'>
                    <h1 style={{color: 'white'}}>Quản lý cho thuê dễ sử dụng!</h1>
                </div>
                <div className='content text-center my-5'>
                    <Container style={{padding: 0}}>
                        Đơn giản hoá việc cho thuê nhà của bạn
                        <br/>
                        Chào tạm biệt với việc làm sổ sách cho việc thuê nhà và trải nghiệm cho thuê nhà với web!
                    </Container>
                </div>
            </section>
        </>

    )
}

export default Hero
