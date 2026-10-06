import useFetch from "../hooks/useFetch";
import SearchBar from "../SearchBar"
import Albums from "./Albums";
import AlbumForm from "../AlbumForm";
import React, { useState } from "react";
import axios from "axios";

//ESTA ES LA PAGINA PRINCIPAL
                             
const SearchResults = ({artist, setArtist}) => {
  const { albums, isLoading, error, refresh } = useFetch("http://localhost:8080/albums", artist);
  const [albumToEdit, setAlbumToEdit] = useState(null);
  
  const cancelarEdicion = () => {
    setAlbumToEdit(null);
  };

  return (
    <div className="results-section">
      <h2>Albumes</h2>
      <section>
        <SearchBar setArtist={setArtist} />
        <AlbumForm onCreated={refresh} albumToEdit={albumToEdit} onCancelEdit={cancelarEdicion}/>
        {isLoading && <p className="loading">Cargando...</p>}
        {error && <p>{error}</p>}

        {!isLoading && !error && (
          // <RenderAlbums albums={albums} />
          <Albums
              albums={albums}
              onEdit={(album) => {
                  setAlbumToEdit(album);
              }}
              onDelete={async (album) => {
                  const confirmar = window.confirm(
                      `¿Seguro que deseas eliminar el álbum "${album.nombre}"?`
                  );

                  if (!confirmar) {
                      return;
                  }

                  try {
                      const response = await axios.delete(`http://localhost:8080/albums/${album.idAlbum}`);
                      if (response.data.success) {
                          refresh();
                      } else {
                          console.error(response.data.message);
                      }

                  } catch (error) {
                      console.error("Error al eliminar el álbum:", error);
                  }
              }}/>
        )}
      </section>
    </div>
  )
}

//nodemon
export default SearchResults;