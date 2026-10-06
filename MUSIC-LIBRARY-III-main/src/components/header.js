import { Link } from "react-router-dom";

const Header = ({appName}) => {
    return (
        <header>
            <div>
                <h1>{appName}</h1>
                <nav >
                    <Link className="header-nav" to="/">Search Results</Link>
                </nav>
            </div>
        </header>
    )
}

export default Header;