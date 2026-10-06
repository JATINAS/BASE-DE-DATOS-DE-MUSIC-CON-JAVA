import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { Link } from "react-router-dom";

const SongDetail = ({artist}) => {
    const {id} = useParams();
    const [songState, setSongState] = useState ([{song: '', isLoading: true, error: null}]);

    useEffect(() => {
        const fetchArtist = async () => {
            try {
                // const response = await axios.get(`https://www.theaudiodb.com/api/v1/json/123/album.php?m=${id}`);
                const response = await axios.get(`http://localhost:8080/albums/${id}`);
                setSongState({song: response.data.album, isLoading: false, error: null});
            } catch (error) {
                console.log(`error al cargar el songdetail`);
                setSongState({song: "", isLoading: false, error: "Failed to fetch song details"});
            }
        };

        fetchArtist();
    }, []);

    if (songState.isLoading) return <p className="loading">Loading...</p>
  
    if (songState.error) return <p>Error loading files</p>


    return (
        <div className="song-details">
            {
                songState.song && songState.song.map((song) => {
                    const {strAlbum, strArtist, intYearReleased, strAlbumThumb} = song;

                    return (
                        <article>
                            <img
                                alt={`${strAlbum} Album Cover`}
                                src={`${strAlbumThumb}`}
                                width="500"
                            />
                            <h2>{strAlbum.toUpperCase()}</h2>
                            <h3>{strArtist}</h3>
                            <h3>{intYearReleased}</h3>
                            <Link to={`/`}><button>Back</button></Link>
                            
                        </article>
                    )
                })
            }
        </div>
    )
}

export default SongDetail;