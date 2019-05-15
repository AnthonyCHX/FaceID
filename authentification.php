<?php
if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $ImageData = $_POST['image'];
        //$ImageData=$_FILES['url']['name'];

        $ImageName = $_POST['name'];
        //$ImageName = $_FILES['url']['name'];
        
        $ImagePath = "uploads/$ImageName";
        $Image="https://wad97.000webhostapp.com/uploads/$ImageName";
        

      
            file_put_contents($ImagePath,base64_decode($ImageData));
            //file_put_contents($ImagePath,$ImageData);
            //echo "Your Image Has Been Uploaded. cool Salut !".$prenom;

            //move_uploaded_file($_FILES['url']['tmp_name'],$ImagePath);
           
try
{
$bdd = new PDO('mysql:host=localhost;dbname=id5068369_logintable;charset=utf8', 'id5068369_root', 'root123',array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
}
catch(Exception $e)
{
        die('Erreur : '.$e->getMessage());
}
  
    $guery="SELECT * FROM images";
  $reponse = $bdd->query($guery);
  //$donnees = $reponse->fetch();


  //$adresseimage=$donnees['url'];



function post($url, $postVars = array()){
    //Transform our POST array into a URL-encoded query string.
    $postStr = http_build_query($postVars);
    //Create an $options array that can be passed into stream_context_create.
    $options = array(
        'http' =>
            array(
                'method'  => 'POST', //We are using the POST HTTP method.
                'header'  => 'Content-type: application/x-www-form-urlencoded',
                'content' => $postStr //Our URL-encoded query string.
            )
    );
    //Pass our $options array into stream_context_create.
    //This will return a stream context resource.
    $streamContext  = stream_context_create($options);
    //Use PHP's file_get_contents function to carry out the request.
    //We pass the $streamContext variable in as a third parameter.
    $result = file_get_contents($url, false, $streamContext);
    //If $result is FALSE, then the request has failed.
    if($result === false){
        //If the request failed, throw an Exception containing
        //the error.
        $error = error_get_last();
        throw new Exception('POST request failed: ' . $error['message']);
    }
    //If everything went OK, return the response.
    return $result;
}

$max_nombre=0;
$max_prenom="";
$max_nom="";
while ($donnees = $reponse->fetch())
{   
    $result = post('https://api-us.faceplusplus.com/facepp/v3/compare', array(
    'api_key' => '0LQ_5a-JE8gvZTbqoD8EB9j_uNBHRgTJ',
    'api_secret' => 'PrxR1alwxHG_jQa-Tu7TNVgPt_BWeDZC',
    'image_url1' => $Image,
    'image_url2' => $donnees['url']
    ));

    $obj = json_decode($result);
    $confidence=$obj->{'confidence'};
    
    if (floatval($confidence)>$max_nombre) {
        $max_nombre=floatval($confidence);
        $max_prenom=$donnees["prenom"];
        $max_nom=$donnees["nom"];
    }
}
$final=" ".$max_prenom." ".$max_nom;
echo $final;

$reponse->closeCursor();   

       
    } else {
        echo "Please Try Again";
    }
?>