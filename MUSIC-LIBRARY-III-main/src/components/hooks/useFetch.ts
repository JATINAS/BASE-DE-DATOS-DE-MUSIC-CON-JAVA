import { useEffect, useState } from "react";
import axios from "axios";

export type Album = {
    idAlbum: number;
    nombre: string;
    lanzamiento: number;
    nombreArtista?: string;
};

const useFetch = (url: string, artist: string) => {
    const [albums, setAlbums] = useState<Album[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [reload, setReload] = useState(0);

    useEffect(() => {
        const fetchAlbums = async () => {
            try {
                setIsLoading(true);
                setError(null);

                const response = await axios.get(url, {params: artist.trim() ? { artista: artist.trim() } : {},});
                setAlbums(response.data?.data ?? []);
            } catch (error) {
                console.error(error);
                setAlbums([]);
                setError("Failed to fetch Albums");
            } finally {
                setIsLoading(false);
            }
        };

        fetchAlbums();

    }, [url, artist, reload]);

    const refresh = () => {
        setReload((previous) => previous + 1);
    };

    return {albums, isLoading, error, refresh,};
};

export default useFetch;