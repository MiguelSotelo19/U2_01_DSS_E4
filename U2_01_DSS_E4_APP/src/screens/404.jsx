import { useNavigate } from "react-router-dom";
import Img404 from '../assets/Img404.png';

export const E404 = () => { 
    const navigate = useNavigate();

    const volver = () => {
        const token = localStorage.getItem("token");
        if (!token || token === "null") {
            navigate("/");
        } else {
            navigate("/Gestion");
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="text-center">
                <img src={Img404} alt="404" width={'200px'} draggable="false" />
                <h1>404 Página no encontrada</h1>
                <p>La página a la que intentas acceder no existe</p>
                <button className="btn btn-info" style={{width:'20%'}} onClick={volver}>Volver</button>
            </div>
        </div>
    );
};
