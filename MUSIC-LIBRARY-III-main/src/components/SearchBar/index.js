import { useState } from "react"

const SearchBar = ({setArtist, artist,isLoading, error}) => {
    const [searchArtist, setSearchArtist] = useState("");

    const handleChange = (e) => {
        const {value} = e.target;
        setSearchArtist(value);    
    }

    // const handleSubmit = (e) => {
    //     if (searchArtist.isLoading) return <p className="loading">Loading...</p>;
  
    //     if (searchArtist.error) return <p>Error loading files</p>
    //     e.preventDefault();
    //     setArtist(searchArtist);    
    // }
    const handleSubmit = (e) => {
        e.preventDefault();
        setArtist(searchArtist.trim());
    };
    

    return (
        <section className="searchBar-section">

                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Artist:</label>
                        <input
                            type="text"
                            name="artist"
                            value={searchArtist.artist}
                            onChange={(e) => handleChange(e)}
                        />
                    </div>
                    <button type="submit">Search</button>
                </form>

        </section>
    )
}


export default SearchBar;