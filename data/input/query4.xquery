declare option output:method "xml";
declare option output:indent "yes";

let $topTags :=
    for $tag in doc("anime/Tags.xml")//row
    let $tagName := $tag/@TagName/string()
    let $count := $tag/@Count/string()
    order by xs:integer($count) descending
    return $tagName

let $topTagQuestions :=
    for $post in doc("anime/Posts.xml")//row
    where some $tag in $topTags satisfies fn:contains($post/@Tags, $tag)
    let $title := $post/@Title/string()
    let $viewCount := $post/@ViewCount/string()
    order by xs:integer($viewCount) descending
    return <Question>
               <Title>{$title}</Title>
               <ViewCount>{$viewCount}</ViewCount>
           </Question>

return subsequence($topTagQuestions, 1, 10)
