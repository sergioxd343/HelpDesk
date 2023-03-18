import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CRUDMongoAtlas {

    public static void main(String[] args) {

        // Cadena de conexión de MongoDB Atlas
        String connectionString = "mongodb+srv://sergioxd343:10012003@clusterexam.nnz7zzt.mongodb.net/examenMongo?retryWrites=true&w=majority";

        // Crear un cliente de MongoDB
        MongoClientURI uri = new MongoClientURI(connectionString);
        // Obtener la base de datos
        try (MongoClient mongoClient = new MongoClient(uri)) {
            // Obtener la base de datos
            MongoDatabase database = mongoClient.getDatabase("ciclovideo");
            // Obtener una colección de la base de datos
            MongoCollection<Document> collection = database.getCollection("videojuegos");
            
            
            //INSERTAR EN MONGO ATLAS
            
//            Document document = new Document("nombre", "Counter Strike Global Ofensive")
//                    .append("fechaLanzamiento", 2006)
//                    .append("plataformas", Arrays.asList("PC", "PS2"))
//                    .append("desarrollador", "VALVE")
//                    .append("distribuidor", "VALVE")
//                    .append("genero", "ACCION-DISPAROS")
//                    .append("clasificacion", "B-Adolecentes")
//                    .append("sinopsis", "Counter Strike: Global Offensive (o simplemente CS:GO) es un videojuego de acción shooter en primera persona. Cuarta entrega de la saga de Valve, CSGO repite el planteamiento de siempre: dos equipos, terroristas y antiterroristas, luchando entre sí por cumplir objetivos como plantar/ desactivar una bomba")
//                    .append("criticas", new Document("Metacritic", 97)
//                            .append("IGN", 10)
//                            .append("GameSpot", 10));
//            // Insertar el documento en la colección
//            collection.insertOne(document);
            // Cerrar la conexión
            
            
            
            //ACTUALIZAR EN MONGO ATLAS
            
            
            // Crear un filtro que identifique el documento que deseas actualizar
//            Document filtro = new Document("nombre", "Counter Strike Global Ofensive");
//
//            // Crear un documento con las modificaciones que deseas realizar
//            Document modificaciones = new Document("$set", new Document("clasificacion", "C-Adultos"));
//
//            // Actualizar el documento en la colección
//            collection.updateOne(filtro, modificaciones);
            
            
            
            // Eliminar un documento de la colección
            
            
            
            //collection.deleteOne(eq("nombre", "Counter Strike Global Ofensive"));
            
            
            
            // Obtener todos los documentos en la colección
            
            
            Iterator<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                
                Document doc = cursor.next();
                System.out.println("----------------------------------------------------------------");
                System.out.println("Nombre: " + doc.getString("nombre"));
                System.out.println("Fecha de lanzamiento: " + doc.getInteger("fechaLanzamiento"));
                System.out.println("Desarrollador: " + doc.getString("desarrollador"));
                System.out.println("Distribuidor: " + doc.getString("distribuidor"));
                System.out.println("Género: " + doc.getString("genero"));
                System.out.println("Clasificación: " + doc.getString("clasificacion"));
                System.out.println("Sinopsis: " + doc.getString("sinopsis"));
                Document criticas = (Document) doc.get("criticas");
                System.out.println("Críticas: ");
                System.out.println("  Metacritic: " + criticas.getInteger("Metacritic"));
                System.out.println("  IGN: " + criticas.getInteger("IGN"));
                System.out.println("  GameSpot: " + criticas.getInteger("GameSpot"));
                System.out.println();
                System.out.println("----------------------------------------------------------------");
            }
            
            
            
            
            
            
            
        }
        
        
    }

}
