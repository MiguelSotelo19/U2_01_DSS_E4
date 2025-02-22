import Img403 from '../assets/Img404.png'

export const E404 = () => { 

    return(
        <>
       <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="text-center">
                <img src={Img403} alt="403" width={'200px'} draggable="false" />
                <h1>404 Página no encontrada</h1>
                <p>La página a la que intentas acceder no existe</p>
            </div>
        </div>
        </>
    );
}