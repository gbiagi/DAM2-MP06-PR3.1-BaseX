xquery version "3.0";

declare option output:method "xml";
declare option output:indent "yes";

let $results :=
  for $question in doc("anime/Posts.xml")//row
  let $title := $question/@Title/string()
  let $viewCount := xs:integer($question/@ViewCount)
  where exists($title) and exists($viewCount)
  order by $viewCount descending
  return <post>
            <title>{$title}</title>
            <viewCount>{$viewCount}</viewCount>
         </post>

return <posts>{$results}</posts>
