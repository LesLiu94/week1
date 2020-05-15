import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faGlobe
} from '@fortawesome/free-solid-svg-icons';

class FooterContent extends React.Component {
    render() {
        return (
            <footer>@2019 An Exercise in Web Development <FontAwesomeIcon icon={faGlobe}/></footer>
        );
    }
}

export default FooterContent;









