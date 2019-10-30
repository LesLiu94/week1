import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faUser
} from '@fortawesome/free-solid-svg-icons';

class FooterContent extends React.Component {
    render() {
        return (
            <footer>@2019 Exercise in using ReactJS <FontAwesomeIcon icon={faUser}/></footer>
        );
    }
}

export default FooterContent;









