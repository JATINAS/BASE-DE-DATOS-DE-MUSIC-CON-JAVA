
const Albums = ({ albums, onEdit, onDelete }) => {
    return (
        <div className="albums">
            {albums.map((album) => (
                <div className="album" key={album.idAlbum}>
                    <h2>{album.nombre}</h2>
                    <p>{album.nombreArtista}</p>
                    <p>{album.lanzamiento}</p>
                    <button type="button" onClick={() => onEdit(album)}>Editar</button>
                    <button type="button" className="delete-button" onClick={() => onDelete(album)}>Eliminar</button>
                </div>
            ))}
        </div>
    );
};

export default Albums;