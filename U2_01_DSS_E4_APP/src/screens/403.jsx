import Img403 from '../assets/Img403.webp'

export const E403 = () => { 

    return(
        <>
       <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="text-center">
                <img src={Img403} alt="403" draggable="false" />
                <h1>403 Acceso denegado</h1>
                <p>No tienes acceso a este sitio!</p>
            </div>
        </div>
        </>
    );
}