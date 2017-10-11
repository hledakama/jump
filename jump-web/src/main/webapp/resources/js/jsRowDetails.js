/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
    return '<div class="slider">'+
        '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
            '<tr>'+
                '<td>Street 1:</td>'+
                '<td>'+d.stree1+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td>Street 2:</td>'+
                '<td>'+d.street2+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td>Street number:</td>'+
                '<td>'+d.streetNumber+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td>City:</td>'+
                '<td>'+d.city+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td>State:</td>'+
                '<td>'+d.state+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td>Zip code:</td>'+
                '<td>'+d.zipcode+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td>Country:</td>'+
                '<td>'+d.country+'</td>'+
            '</tr>'+
        '</table>'+
    '</div>';
}

$(document).ready(function() {
    var dt = $('#itemdata_table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "scripts/ids-objects.php",
        "columns": [
            {
                "class":          "details-control",
                "orderable":      false,
                "data":           null,
                "defaultContent": ""
            },
            { "data": "ShowHide" },
            { "data": "Date" },
            { "data": "Avatar" },
            { "data": "Comment" },
            { "data": "Duration" },
            { "data": "Unit" },
            { "data": "Remove" },
            { "data": "Edit" }
        ],
        "order": [[1, 'asc']]
    } );
 
    // Array to track the ids of the details displayed rows
    var detailRows = [];
 
    $('#itemdata_table tbody').on( 'click', 'tr td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = dt.row( tr );
        var idx = $.inArray( tr.attr('id'), detailRows );
 
        if ( row.child.isShown() ) {
            tr.removeClass( 'details' );
            row.child.hide();
 
            // Remove from the 'open' array
            detailRows.splice( idx, 1 );
        }
        else {
            tr.addClass( 'details' );
            row.child( format( row.data() ) ).show();
 
            // Add to the 'open' array
            if ( idx === -1 ) {
                detailRows.push( tr.attr('id') );
            }
        }
    } );
 
    // On each draw, loop over the `detailRows` array and show any child rows
    dt.on( 'draw', function () {
        $.each( detailRows, function ( i, id ) {
            $('#'+id+' td.details-control').trigger( 'click' );
        } );
    } );
} );


