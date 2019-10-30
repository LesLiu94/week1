import React from "react";
import logo from '../../yeoman.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faLeaf,
    faExchangeAlt
} from '@fortawesome/free-solid-svg-icons';
import { faReact } from '@fortawesome/free-brands-svg-icons';

class Home extends React.Component {
    render() {
        return (
            <div className="jumbotron">
                <h1>'Greetings!'</h1>
                <img src={logo} alt="Yeoman"/>
                <div className="home-paragraph">This is a project where I practice using the following technologies:</div>
                <dl>
                    <dt className="main-links"> <a href="https://spring.io/" target="_blank" rel="noopener noreferrer">Spring</a> <FontAwesomeIcon icon={faLeaf}/> </dt>
                    <dd className="main-desc">builds back-end production-ready applications</dd>
                    <dt className="main-links"><a href="https://swagger.io/" target="_blank" rel="noopener noreferrer">Swagger</a> <FontAwesomeIcon icon={faExchangeAlt}/></dt>
                    <dd className="main-desc">consumes RESTful web services</dd>
                    <dt className="main-links"><a href="https://reactjs.org/" target="_blank" rel="noopener noreferrer">ReactJS</a> <FontAwesomeIcon icon={faReact}/></dt>
                    <dd className="main-desc">develops front end single-page applications</dd>
                </dl>
            </div>
        );
    }
}

export default Home;