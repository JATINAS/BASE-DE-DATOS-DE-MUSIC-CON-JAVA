import { useEffect, useState } from "react";
import axios from "axios";

const AlbumForm = ({ onCreated, albumToEdit, onCancelEdit }) => {
    const [nombre, setNombre] = useState("");
    const [lanzamiento, setLanzamiento] = useState("");
    const [idArtista, setIdArtista] = useState("");

    const [artistas, setArtistas] = useState([]);
    const [mensaje, setMensaje] = useState("");
    const [tipoMensaje, setTipoMensaje] = useState("");
    const [error, setError] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const obtenerArtistas = async () => {
            try {
                const response = await axios.get("http://localhost:8080/artistas");
                console.log("Respuesta artistas:", response.data);
                setArtistas(response.data.data || []);
            } catch (error) {
                console.error("Error al obtener artistas:", error);
                setError("No se pudieron cargar los artistas");
            }
        };

        obtenerArtistas();
    }, []);

    useEffect(() => {
        if (albumToEdit && artistas.length > 0) {
            setNombre(albumToEdit.nombre);
            setLanzamiento(albumToEdit.lanzamiento);

            const artista = artistas.find(
                (artista) => artista.nombre === albumToEdit.nombreArtista
            );

            if (artista) {
                setIdArtista(String(artista.idArtista));
            }
        }
    }, [albumToEdit, artistas]);

    useEffect(() => {
        if (!mensaje) return;
        const timer = setTimeout(() => {
            setMensaje("");
            setTipoMensaje("");
        }, 3000);
    }, [mensaje]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMensaje("");
        setError("");

        if (!nombre.trim() || !lanzamiento || !idArtista) {
            setError("Todos los campos son obligatorios");
            return;
        }

        try {
            setIsLoading(true);
            const album = {
                nombre: nombre.trim(),
                lanzamiento: Number(lanzamiento),
                artista: {
                    idArtista: Number(idArtista)
                }
            };
            let response;

            if (albumToEdit) {
                response = await axios.put( `http://localhost:8080/albums/${albumToEdit.idAlbum}`, album);
            } else {
                response = await axios.post("http://localhost:8080/albums", album);
            }

           if (response.data.success) {
                setMensaje(albumToEdit ? "Álbum actualizado correctamente" : "Álbum creado correctamente");
                setTipoMensaje("success");
                setNombre("");
                setLanzamiento("");
                setIdArtista("");

                if (albumToEdit && onCancelEdit) {
                    onCancelEdit();
                }

                if (onCreated) {
                    onCreated();
                }
            }
        } catch (error) {
            console.error("Error al guardar el álbum:", error);
            setMensaje(albumToEdit ? "No se pudo actualizar el álbum" : "No se pudo crear el álbum");
            setTipoMensaje("error");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <section className="album-form">
            <h2>{albumToEdit ? "Editar álbum" : "Agregar álbum"}</h2>
            {mensaje && (
                <p className="mensaje">
                    {mensaje}
                </p>
            )}

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nombre del álbum:</label>
                    <input
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        placeholder="Ej. A Night at the Opera"
                    />
                </div>

                <div>
                    <label>Año de lanzamiento:</label>
                    <input
                        type="number"
                        value={lanzamiento}
                        onChange={(e) => setLanzamiento(e.target.value)}
                        placeholder="Ej. 1975"
                    />
                </div>

                <div>
                    <label>Artista:</label>
                    <select value={idArtista} onChange={(e) => setIdArtista(e.target.value)}>
                        <option value="">
                            -- Selecciona un artista --
                        </option>

                        {artistas.map((artista) => (
                            <option key={artista.idArtista}value={artista.idArtista}>
                                {artista.nombre}
                            </option>
                        ))}
                    </select>
                </div>

                <button type="submit" disabled={isLoading}>
                    {
                        isLoading ? "Guardando..." : albumToEdit ? "Actualizar álbum" : "Guardar álbum"
                    }
                </button>

                {albumToEdit && (
                    <button type="button" className="cancel-button"
                        onClick={() => {
                            setNombre("");
                            setLanzamiento("");
                            setIdArtista("");
                            onCancelEdit();
                        }}>
                        Cancelar
                    </button>
                )}

            </form>

            {mensaje && (
                <p className={`mensaje ${tipoMensaje}`}>
                    {mensaje}
                </p>
            )}
            {error && <p>{error}</p>}
        </section>
    );
};

export default AlbumForm;