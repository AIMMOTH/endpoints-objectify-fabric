Fabric
======

Maven
-----

Run with: mvn appengine:devserver

Hot deploy new code with (new prompt): mvn package

Eclipse
-------

Import as Maven project.

Run Locally
-----------

1) Run with maven (above)

2) Go to http://localhost:8080

3) Examine the JavaScript console

4) Click the textarea

5) Optional: Check the API at http://localhost:8080/_ah/api/explorer (run "unsecure" JavaScript)

JavaScript
----------

It's easy to use the generated JavaScript.

1) Create an EntityId with POST. Returns its id.

<pre>gapi.client.fabric.fabric.postEntityId({}).execute(function(resp) {console.dir(resp);});</pre>

2) Create an EntityData with the same id as return in 4.1. This entity contains a description text.

<pre>gapi.client.fabric.fabric.postEntityData({entityId: "5207287069147136", description: "massa text"}).execute(function(resp) {console.dir(resp);});</pre>

3) Create an EntityPrimitives with various data and with the same id as 4.1

<pre>gapi.client.fabric.fabric.postEntityPrimitives({entityId: "5207287069147136", email: "carl@workaround.io"}).execute(function(resp) {console.dir(resp);});</pre>

4) Collect all three entities with the same id as 4.1 and by specifying what types to return. Types to return are separated with whitespace.

<pre>gapi.client.fabric.fabric.getParentByIdWithChildren({id: "5207287069147136", parent:"entityid", children:"entitydata entityprimitives"}).execute(function(resp) {console.dir(resp);});</pre>